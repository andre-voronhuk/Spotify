/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.spotify.view;

import com.spotify.controller.Controller;
import com.spotify.model.Musica;
import com.spotify.model.Playlist;
import com.spotify.model.Usuario;
import jaco.mp3.player.MP3Player;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.Element;
import others.PlayMP3;

/**
 *
 * @author renea
 */
public class TelaInicial extends javax.swing.JFrame {

    /**
     * Creates new form TelaInicial
     */
    Controller controller;
    Usuario usuarioLogado;
    List<Playlist> playlists;
    PlayMP3 player = new PlayMP3();
    List<String> musicasTocadas = new ArrayList<>();

    TelaInicial(Controller controller) {
        initComponents();
        this.controller = controller;
        this.usuarioLogado = controller.getUser();

        jLabelNome.setText(usuarioLogado.getNome());
        esconderPainelADM();
        atualizarPlaylists();
        atualizarMusicas("Todas as Musicas");

    }

    private void esconderPainelADM() {
        if (!usuarioLogado.getFuncao()) {
            jButtonAdministrador.setVisible(false);
        }

    }

    private void atualizarPlaylists() {

        List<Playlist> playlists = usuarioLogado.getPlaylist();

        DefaultListModel model = new DefaultListModel<>();
        model.add(0, "Todas as Musicas");
        int i = 1;
        for (Playlist playlist : playlists) {
            model.add(i, playlist.getNome());

        }
        jListPlaylists.setModel(model);
        jListPlaylists.setSelectedIndex(0);
        atualizarMusicas("Todas as Musicas");
    }

    private void atualizarMusicas(String nomePlaylist) {

        List<Musica> musicas = controller.buscarMusicasPlaylist(nomePlaylist);
        if (nomePlaylist == "Todas as Musicas") {
            musicas = controller.buscarMusicasPlaylist("Todas as Musicas");
        }
        DefaultListModel model = new DefaultListModel<>();
        int i = 0;
        for (Musica musica : musicas) {
            model.add(i, musica.getNome());
            i++;
        }
        jListMusicas.setModel(model);
        jListMusicas.setSelectedIndex(0);
        jLabelNomePlaylist.setText(nomePlaylist);

    }

    private void adicionarFila(String nomeMusica) {
        DefaultListModel model = new DefaultListModel<>();

        int i = 0;

        while (i < jListFila.getModel().getSize()) {
            //itera por todos os elementos
            model.add(i, jListFila.getModel().getElementAt(i));
            i++;
        }
        model.add(i, nomeMusica);
        jListFila.setModel(model);

        atualizarDadosMusica();

    }

    private void adicionarAoTopo(String nomeMusica) {
        DefaultListModel model = new DefaultListModel<>();

        model.add(0, nomeMusica);
        int i = 1;
        while (i < jListFila.getModel().getSize() + 1) {
            //itera por todos os elementos
            model.add(i, jListFila.getModel().getElementAt(i - 1));
            i++;
        }

        jListFila.setModel(model);

        atualizarDadosMusica();

    }

    private void atualizarDadosMusica() {
        String nomeMusica = null;
        try {
            nomeMusica = jListFila.getModel().getElementAt(0);
        } catch (Exception e) {
        }

        if (nomeMusica == null) {
            jLabelNomeMusica.setText("Nenhuma Musica Selecionada");
            jLabelNomeArtista.setText("***");
            jLabelDuracao.setText("0:00");
            PlayMP3.mp3Player.stop();
            PlayMP3.tocando = false;
        } else {

            Musica musica = this.controller.buscarMusica(nomeMusica);

            jLabelNomeMusica.setText(musica.getNome());
            jLabelNomeArtista.setText(musica.getArtista());
            jLabelEstilo.setText(musica.getEstilo());
            jLabelDuracao.setText(this.controller.atualizarDuracao(musica.getCaminho()));

        }
    }

    private void buscarEtocar(String nomeMusica, boolean topo) {
        Musica musica = this.controller.buscarMusica(nomeMusica);
        if (topo) {
            adicionarAoTopo(nomeMusica);
        } else {
            adicionarFila(nomeMusica);
        }

        if (!PlayMP3.tocando) {
            this.player.play(musica.getCaminho());
            PlayMP3.tocando = true;
        } else {

            File f = new File(musica.getCaminho());
            PlayMP3.mp3Player.addToPlayList(f);

        }
        atualizarDadosMusica();
    }

    private void guardarMusicasTocadas(String nomeMusica) {
        this.musicasTocadas.add(this.musicasTocadas.size(), nomeMusica);

    }

    private void proximaMusica() {
        PlayMP3.mp3Player.skipForward();
        DefaultListModel model = new DefaultListModel<>();
        try {
            String musicaPulada = jListFila.getModel().getElementAt(0);
            guardarMusicasTocadas(musicaPulada);

        } catch (Exception e) {
        }

        int i = 1;
        while (i < jListFila.getModel().getSize()) {
            //itera por todos os elementos removendo o 1º elemento
            model.add(i - 1, jListFila.getModel().getElementAt(i));
            i++;
        }

        jListFila.setModel(model);
        atualizarDadosMusica();
    }

    private void voltarMusica() {
        PlayMP3.mp3Player.skipBackward();

        try {
            String ultima = musicasTocadas.get(musicasTocadas.size() - 1);
            musicasTocadas.remove(musicasTocadas.lastIndexOf(ultima));

            buscarEtocar(ultima, true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sem musicas para voltar!");

        }

    }

    public String exibirAdicionarPlaylists() {
        //i solved my problem adding the following 2 lines of code...
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        String selectionString = "";
        Object[] options;

        List<Playlist> playlists = this.controller.buscarPlaylist();

        if (playlists.isEmpty()) {
            return null;
        } else {
            List<String> playlistsNome = new ArrayList<>();

            for (Playlist playlist : playlists) {
                playlistsNome.add(playlist.getNome());
            }
            try {
                Object selectionObject = JOptionPane.showInputDialog(frame, "Escolha a Playlist", "Playlists", JOptionPane.PLAIN_MESSAGE, null, playlistsNome.toArray(), playlistsNome.get(0));
                selectionString = selectionObject.toString();
            } catch (Exception e) {

            }

            return selectionString;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jButtonPlay = new javax.swing.JButton();
        jButtonAvancar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListMusicas = new javax.swing.JList<>();
        jLabelNomeMusica = new javax.swing.JLabel();
        jLabelNomeArtista = new javax.swing.JLabel();
        jLabelDuracao = new javax.swing.JLabel();
        jButtonAdicionarNaPlaylist = new javax.swing.JButton();
        jLabelNomePlaylist = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelEstilo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButtonBuscar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListPlaylists = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButtonExcluirPlaylist = new javax.swing.JButton();
        jPanelUsuario = new javax.swing.JPanel();
        jLabelNome = new javax.swing.JLabel();
        jButtonPerfil = new javax.swing.JButton();
        jButtonAdministrador = new javax.swing.JButton();
        jButtonSair = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListFila = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spotify - Home");
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        jButtonPlay.setBackground(new java.awt.Color(51, 51, 51));
        jButtonPlay.setText("Play");
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlayActionPerformed(evt);
            }
        });

        jButtonAvancar.setBackground(new java.awt.Color(51, 51, 51));
        jButtonAvancar.setText(">l");
        jButtonAvancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAvancarActionPerformed(evt);
            }
        });

        jButtonVoltar.setBackground(new java.awt.Color(51, 51, 51));
        jButtonVoltar.setText("l<");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jListMusicas.setBackground(new java.awt.Color(61, 61, 61));
        jListMusicas.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jListMusicas.setToolTipText("");
        jListMusicas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListMusicasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListMusicas);

        jLabelNomeMusica.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelNomeMusica.setText("Nenhuma Musica Selecionada");

        jLabelNomeArtista.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelNomeArtista.setText("Nome do Artista");

        jLabelDuracao.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelDuracao.setText("2:30");

        jButtonAdicionarNaPlaylist.setBackground(new java.awt.Color(51, 51, 51));
        jButtonAdicionarNaPlaylist.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButtonAdicionarNaPlaylist.setText("+");
        jButtonAdicionarNaPlaylist.setToolTipText("Adicionar Música na PlayList");
        jButtonAdicionarNaPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarNaPlaylistActionPerformed(evt);
            }
        });

        jLabel2.setText("Estilo:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAvancar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelNomePlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelNomeMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(277, 277, 277)
                                .addComponent(jButtonAdicionarNaPlaylist)
                                .addGap(23, 23, 23))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelNomeArtista)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelDuracao)
                                .addGap(31, 31, 31))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelEstilo)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNomePlaylist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeMusica, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdicionarNaPlaylist))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeArtista)
                    .addComponent(jLabelDuracao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelEstilo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVoltar)
                    .addComponent(jButtonAvancar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        jButtonBuscar.setBackground(new java.awt.Color(51, 51, 51));
        jButtonBuscar.setText("Buscar");

        jListPlaylists.setBackground(new java.awt.Color(61, 61, 61));
        jListPlaylists.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListPlaylists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListPlaylistsMouseClicked(evt);
            }
        });
        jListPlaylists.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListPlaylistsValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jListPlaylists);

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setText("Criar Playlist");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonExcluirPlaylist.setBackground(new java.awt.Color(51, 51, 51));
        jButtonExcluirPlaylist.setText("Excluir Playlist");
        jButtonExcluirPlaylist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirPlaylistActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonExcluirPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonBuscar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButtonExcluirPlaylist))
                .addContainerGap())
        );

        jPanelUsuario.setBackground(new java.awt.Color(51, 51, 51));

        jLabelNome.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelNome.setText("Nome do Usuário");

        jButtonPerfil.setBackground(new java.awt.Color(51, 51, 51));
        jButtonPerfil.setText("Perfil");
        jButtonPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPerfilActionPerformed(evt);
            }
        });

        jButtonAdministrador.setBackground(new java.awt.Color(51, 51, 51));
        jButtonAdministrador.setText("Painel Administrador");
        jButtonAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdministradorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelUsuarioLayout = new javax.swing.GroupLayout(jPanelUsuario);
        jPanelUsuario.setLayout(jPanelUsuarioLayout);
        jPanelUsuarioLayout.setHorizontalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                        .addComponent(jLabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUsuarioLayout.createSequentialGroup()
                        .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonPerfil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAdministrador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanelUsuarioLayout.setVerticalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdministrador)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jListFila.setBackground(new java.awt.Color(61, 61, 61));
        jListFila.setEnabled(false);
        jScrollPane4.setViewportView(jListFila);

        jLabel1.setText("Fila de Reprodução");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSair)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdicionarNaPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarNaPlaylistActionPerformed
        // TODO add your handling code here:

        String nomeMusica = "";
        String playlist = "";
        try {
            nomeMusica = jListFila.getModel().getElementAt(0);
        } catch (Exception e) {
            //fila vazia
        }
        if (nomeMusica.equals("")) {
            JOptionPane.showMessageDialog(this, "Nenhuma musica tocando");

        } else {
            playlist = exibirAdicionarPlaylists();

            if (playlist == null) {

                JOptionPane.showMessageDialog(this, "Voce ainda nao possui nenhuma playlist!\nredirecionando para criação de playlist...");
                jButton1ActionPerformed(evt);
                jButtonAdicionarNaPlaylistActionPerformed(evt);
            }
            if (playlist != null) {

                boolean result = this.controller.adicionarMusicaEmPlaylist(nomeMusica, playlist);

                if (result) {
                    JOptionPane.showMessageDialog(this, nomeMusica + " foi adicionada em " + playlist);
                }
            }
        }


    }//GEN-LAST:event_jButtonAdicionarNaPlaylistActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        PlayMP3.mp3Player.stop(); // para a musica dentro da classe
        PlayMP3.tocando = false;  // atualiza o estado da variavel estatica

        controller.abrirTela(this, "login");
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String novaPlaylist = JOptionPane.showInputDialog("Nome da Playlist");

        if (novaPlaylist != null) {
            this.controller.criarPlaylist(novaPlaylist);
        }
        atualizarPlaylists();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jListPlaylistsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListPlaylistsMouseClicked
        // ENTRA AQUI QUANDO USUARIO SELECIONA UMA PLAYLIST

        atualizarMusicas(jListPlaylists.getSelectedValue());


    }//GEN-LAST:event_jListPlaylistsMouseClicked

    private void jListPlaylistsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListPlaylistsValueChanged


    }//GEN-LAST:event_jListPlaylistsValueChanged

    private void jButtonAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdministradorActionPerformed
        this.setVisible(false);
        this.controller.abrirTela(null, "administrador");
    }//GEN-LAST:event_jButtonAdministradorActionPerformed

    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
        // TODO add your handling code here:

        if (PlayMP3.tocando) {
            PlayMP3.mp3Player.pause();
            PlayMP3.tocando = false;

        } else {
            if (PlayMP3.mp3Player.isPaused()) {

                PlayMP3.mp3Player.play();

            } else {
                String nomeMusica = jListMusicas.getSelectedValue();
                buscarEtocar(nomeMusica, false);
            }

            PlayMP3.tocando = true;

        }


    }//GEN-LAST:event_jButtonPlayActionPerformed

    private void jListMusicasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListMusicasMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            String nomeMusica = jListMusicas.getSelectedValue();
            buscarEtocar(nomeMusica, false);
        }
    }//GEN-LAST:event_jListMusicasMouseClicked

    private void jButtonAvancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAvancarActionPerformed
        // TODO add your handling code here:
        if (!PlayMP3.tocando) {
            PlayMP3.mp3Player.play();
            PlayMP3.tocando = true;
        }
        if (jListFila.getModel().getSize() > 1) {
            proximaMusica();
        } else {
            PlayMP3.mp3Player.pause();
            PlayMP3.tocando = false;
            JOptionPane.showMessageDialog(this, "Sem musicas na fila!");
        }

    }//GEN-LAST:event_jButtonAvancarActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // TODO add your handling code here:
        voltarMusica();

    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPerfilActionPerformed
        this.controller.abrirTela(this, "telaUsuario");
    }//GEN-LAST:event_jButtonPerfilActionPerformed

    private void jButtonExcluirPlaylistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirPlaylistActionPerformed
        // TODO add your handling code here:
        this.controller.excluirPlaylist(jListPlaylists.getSelectedValue());
        atualizarPlaylists();
    }//GEN-LAST:event_jButtonExcluirPlaylistActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

    }//GEN-LAST:event_formMouseMoved

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdicionarNaPlaylist;
    private javax.swing.JButton jButtonAdministrador;
    private javax.swing.JButton jButtonAvancar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonExcluirPlaylist;
    private javax.swing.JButton jButtonPerfil;
    private javax.swing.JButton jButtonPlay;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelDuracao;
    private javax.swing.JLabel jLabelEstilo;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelNomeArtista;
    private javax.swing.JLabel jLabelNomeMusica;
    private javax.swing.JLabel jLabelNomePlaylist;
    private javax.swing.JList<String> jListFila;
    private javax.swing.JList<String> jListMusicas;
    private javax.swing.JList<String> jListPlaylists;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
