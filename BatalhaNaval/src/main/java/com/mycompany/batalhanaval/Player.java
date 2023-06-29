package com.mycompany.batalhanaval;

import java.util.Scanner;

public class Player {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        char modo = ' ';
        char espaco = ' ';
        boolean vez = true;
        boolean fim = false;
        int[] tiro = new int[2];
        int tiroRes = 0;
        String ganhador = "";
        
        
        boolean continua = false;
        boolean prox = false;
        
        
        char[][] matriz1 = BatalhaNaval.geraMatriz();
        char[][] ataque1 = BatalhaNaval.geraMatriz(); 
        char[][] matriz2 = BatalhaNaval.geraMatriz(); 
        char[][] ataque2 = BatalhaNaval.geraMatriz(); 
        
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("| [1] Contra a maquina   |");
        System.out.println("| [2] Contra um amigo         |");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        do {
            modo = ler.next().charAt(0);
            prox = BatalhaNaval.conferirOpcao(modo);
        } while (!prox); 

        if (modo == '1') { 
            matriz2 = BatalhaNaval.auto(matriz2);

            espaco = BatalhaNaval.espaco(); 
            if (espaco == '1') { 
                matriz1 = BatalhaNaval.auto(matriz1);
            }
            if (espaco == '2') { 
                matriz1 = BatalhaNaval.manual(matriz1);
            }
        }

        if (modo == '2') { 

            espaco = BatalhaNaval.espaco();
            if (espaco == '1') { 
                matriz1 = BatalhaNaval.auto(matriz1);
            }
            if (espaco == '2') {
                matriz1 = BatalhaNaval.manual(matriz1);
            }

            espaco = BatalhaNaval.espaco();
            if (espaco == '1') {
                matriz2 = BatalhaNaval.auto(matriz2);
            }
            if (espaco == '2') {
                matriz2 = BatalhaNaval.manual(matriz2);
            }
        }

        do {
            if (vez) {
                System.out.println("\nVez do jogador 1:  ");

                do {
                    BatalhaNaval.mostrarTabuleiro(ataque2);

                    System.out.print("aonde deseja atirar capitão?: ");

                    do {
                        String pos = ler.next(); 
                        tiro = BatalhaNaval.converteLetra(pos); 
                        prox = BatalhaNaval.conferirTiro(tiro); 
                        if (!prox)
                            System.out.print("ops, digite novamente: ");
                    } while (!prox);

                    tiroRes = BatalhaNaval.statusTiro(tiro, matriz2);
                    ataque2 = BatalhaNaval.atirar(tiro, ataque2, tiroRes);
                    matriz2 = BatalhaNaval.atirar(tiro, matriz2, tiroRes);

                    switch (tiroRes) {
                        case 1 -> {
                            System.out.println("\n Acertou um barco!");
                            vez = true;
                        }
                        case 2 -> {
                            System.out.println("\n Ai ja foi tente outra posição: ");
                            vez = true;
                        }
                        case 3 -> {
                            System.out.println("\n Acertou a agua!");
                            vez = false;
                        }
                    }

                    fim = BatalhaNaval.contar(ataque2); 
                    if (fim)
                        ganhador = "Jogador 1";
                } while (vez && !fim);
            } else { 
                System.out.println("\nVez do jogador 2");

                do {
                    if (modo == '1') { 
                        tiro = BatalhaNaval.gerarTiro(matriz1); 
                    }

                    if (modo == '2') { 
                        BatalhaNaval.mostrarTabuleiro(ataque1);
                        System.out.print("aonde deseja atirar?");
                        do {
                            String pos = ler.next();
                            tiro = BatalhaNaval.converteLetra(pos);
                            prox = BatalhaNaval.conferirTiro(tiro);

                            if (!prox)
                                System.out.print("digite novamente: ");
                        } while (!prox);
                    }

                    tiroRes = BatalhaNaval.statusTiro(tiro, matriz1);
                    ataque1 = BatalhaNaval.atirar(tiro, ataque1, tiroRes);
                    matriz1 = BatalhaNaval.atirar(tiro, matriz1, tiroRes);

                    if (modo == '2')
                        BatalhaNaval.mostrarTabuleiro(ataque1);

                    switch (tiroRes) {
                        case 1 -> {
                            System.out.println("\n Acertou um barco"
                                    + "!");
                            vez = false;
                        }
                        case 2 -> {
                            System.out.println("\n Ai ja foi tente outra posição:");
                            vez = false;
                        }
                        case 3 -> {
                            System.out.println("\n Acertou a agua!");
                            vez = true;
                        }
                    }

                    fim = BatalhaNaval.contar(ataque1);
                    if (fim)
                        ganhador = "Jogador 2";
                } while (!vez && !fim);
            }
        } while (!fim);

        System.out.println("\n" + ganhador + " ganhou o jogo!!!\n");
    }
}