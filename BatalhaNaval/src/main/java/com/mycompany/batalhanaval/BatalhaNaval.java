package com.mycompany.batalhanaval;


import java.util.Scanner;
import java.util.Random;

public class BatalhaNaval {

    public static char[][] geraMatriz() {
    char[][] matriz = new char[11][11];
    matriz[0] = new char[]{'*', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    for (int i = 1; i < matriz.length; i++) {
        matriz[i][0] = (char) ('0' + (i - 1));
        for (int j = 1; j < matriz[i].length; j++) {
            matriz[i][j] = ' ';
        }
    }
    return matriz;
}

    public static boolean conferirTiro(int[] tiro) {
        if (tiro.length != 2) { 
            return false; 
        } else {
            int coluna = tiro[0];
            int linha = tiro[1];

            if (coluna >= 1 && coluna <= 10) { 
                if (linha >= 1 && linha <= 10) { 
                    return true; 
                }
            }

            return false;
        }
    }
    
    public static boolean conferirOpcao(char opcao){ 
        if(opcao=='1'||opcao=='2'){
            return true;
        }else{
            System.out.println("Insira uma opção válida!!!");
            return false; 
        }
    }
    
    public static void mostrarTabuleiro(char matriz[][]) {
        System.out.println(" +----+----+----+----+----+----+----+----+----+");
        for (char[] matriz1 : matriz) {
            for (int j = 0; j < matriz1.length; j++) {
                System.out.printf("| %c ", matriz1[j]);
            }
            System.out.print("|");
            System.out.println("\n +----+----+----+----+----+----+----+----+----+");
        }
}
    
    public static int[] converteLetra(String tiro){ 
        if(tiro.length()!=2) 
            tiro = "x"; 
        int[] nTiro = {tiro.toLowerCase().charAt(0)-97+1, tiro.charAt(1)-48+1}; 

        return nTiro;
    }
    
    public static boolean conferirPosicao(int tamanho, char matriz[][], int[] tiro, boolean sentido){ 
        if(tiro[0]<1||tiro[0]>10||tiro[1]<1||tiro[1]>10){ 
            return false;
        }
        if(sentido){ 
            int fimBarco = tiro[1]+tamanho-1; 
            if(fimBarco>10) { 
                return false; 
            }else{
                for(int i=tiro[1];i<=fimBarco;i++){ 
                    if(matriz[i][tiro[0]]=='B'){
                        return false;
                    }
                }
                return true;
            }   
        }else{ 
            int fimBarco = tiro[0]+tamanho-1; 
            if(fimBarco>10) { 
                return false;
            }else{
                for(int j=tiro[0];j<=fimBarco;j++){
                    if(matriz[tiro[1]][j]=='B'){
                        return false;
                    }
                }
                return true;
            }   
        }      
    }
    
    public static char[][] insereNavio(int tamanho, char matriz[][], int[] tiro, boolean sentido){ 
        if(sentido){
            int fimBarco = tiro[1]+tamanho-1;
            for(int i=tiro[1];i<=fimBarco;i++){
                matriz[i][tiro[0]] = 'B';
            }
        }else{ 
            int fimBarco = tiro[0]+tamanho-1;
            for(int j=tiro[0];j<=fimBarco;j++){ 
                matriz[tiro[1]][j] = 'B';
            }
        }    
        return matriz;
    }
    
    public static int[] gerar(){ 
        Random ale = new Random();
        boolean prox = false;
        int[] tiro = new int[2];
        do{
            tiro[0] = ale.nextInt(10)+1;
            tiro[1] = ale.nextInt(10)+1;            
            prox = conferirTiro(tiro);
        }while(!prox);
        return tiro;
    }
    
    public static int[] gerarTiro(char matriz[][]){ 
        Random ale = new Random();
        boolean prox = false;
        int[] tiro = new int[2];
        do{
            do{
                tiro[0] = ale.nextInt(10)+1;
                tiro[1] = ale.nextInt(10)+1;  
                if(matriz[tiro[1]][tiro[0]]=='X'||matriz[tiro[1]][tiro[0]]=='A') 
                    prox = false;
                else
                    prox = true;
            }while(!prox);                    
            prox = conferirTiro(tiro);
        }while(!prox);
        return tiro;
    }
    
    public static boolean posicao(){
        Scanner ler = new Scanner(System.in);
        System.out.print("\nInsira a orientação do navio: ");
        System.out.print("Vertical\t[1]\n");
        System.out.print("Horizontal\t[2]\n");
        boolean prox = false;
        char orientacao = 0;
        boolean orientacaoBoolean = false;
        do{
            orientacao = ler.next().charAt(0); 
            prox = BatalhaNaval.conferirOpcao(orientacao);
        }while(!prox);
        if(orientacao=='1')
            orientacaoBoolean = true;
        else
            orientacaoBoolean = false;
        return orientacaoBoolean;
    }
    
        public static char[][] autoinsertNavios(char[][] matriz, int num, int tam) {
            Random ale = new Random();
            boolean prox = false;
            int barcosInseridos = 0;

            while (barcosInseridos < num) {
                int[] tiro = gerar();
                boolean orientacao = ale.nextBoolean();
                if (conferirPosicao(tam, matriz, tiro, orientacao)) {
                    matriz = insereNavio(tam, matriz, tiro, orientacao);
                    barcosInseridos++;
                }
            }

            return matriz;
        }
        public static char espaco(){
        Scanner ler = new Scanner(System.in);
        System.out.println("\nComo quer colocar os barcos?: ");
        System.out.print("Automaticamente: digite 1 \n");
        System.out.print("Manualmente: digite 2:\n");
        boolean prox = false;
        char modo = ' ';
        
        do{
            modo = ler.next().charAt(0);
            prox = BatalhaNaval.conferirOpcao(modo);
        }while(!prox);
        return modo; 
    }
    
    
    public static char[][] manualinsertNavios(int tam, char[][] matriz){ 
        Scanner ler = new Scanner(System.in);
        int[] tiro = new int[2];
        boolean orientacao = false;
        boolean prox = false;
        do{
            switch(tam){
                case 1 -> System.out.print("\nInsira a coordenada para um navio(1): ");
                case 2 -> System.out.print("\nInsira a coordenada para um navio(2): ");
                case 3 -> System.out.print("\nInsira a coordenada para um navio(3): ");
                case 4 -> System.out.print("\nInsira a coordenada para um navio(4): ");
            }
            String pos = ler.next();
            tiro = BatalhaNaval.converteLetra(pos);
            prox = BatalhaNaval.conferirPosicao(tam, matriz, tiro, orientacao);
            if(!prox){
                System.out.print("Posição inválida!!!");
                continue; 
            }
            if(tam!=1)
                orientacao = BatalhaNaval.posicao(); 
            prox = BatalhaNaval.conferirPosicao(tam, matriz, tiro, orientacao);
            if(!prox) 
                System.out.print("Posição inválida!!!");
            else 
                matriz = insereNavio(tam, matriz, tiro, orientacao);
        }while(!prox); 
        return matriz;
    }
    
    public static char[][] manual(char[][] matriz){ 
        BatalhaNaval.mostrarTabuleiro(matriz);
        matriz = BatalhaNaval.manualinsertNavios(4, matriz);
            
        for(int i=0; i<2; i++){
            BatalhaNaval.mostrarTabuleiro(matriz);                
            matriz = BatalhaNaval.manualinsertNavios(3, matriz);
        }
        for(int i=0; i<3; i++){ 
            BatalhaNaval.mostrarTabuleiro(matriz);                
            matriz = BatalhaNaval.manualinsertNavios(2, matriz);
        }
        for(int i=0; i<4; i++){
            BatalhaNaval.mostrarTabuleiro(matriz);                
            matriz = BatalhaNaval.manualinsertNavios(1, matriz);
        }
        return matriz;
    }
    
    public static char[][] auto(char[][] matriz){ 
        matriz = BatalhaNaval.autoinsertNavios(matriz, 1, 4);            
        matriz = BatalhaNaval.autoinsertNavios(matriz, 2, 3);            
        matriz = BatalhaNaval.autoinsertNavios(matriz, 3, 2);            
        matriz = BatalhaNaval.autoinsertNavios(matriz, 4, 1);
        return matriz; 
    }
    
    public static int statusTiro(int[] tiro, char[][] matriz){
        return switch (matriz[tiro[1]][tiro[0]]) {
            case 'B' -> 1;
            case 'X', 'O' -> 2;
            default -> 3;
        };
    }
    
    public static char[][] atirar(int[] tiro, char[][] matriz, int statusTiro){
        if(statusTiro==1){
            matriz[tiro[1]][tiro[0]] = 'X';        
        }else if(statusTiro==3){
            matriz[tiro[1]][tiro[0]] = 'O'; 
        }
        return matriz;
    }
    
    
    public static boolean contar(char[][] matriz){
        int tiros = 0;
        for(char[] linha : matriz){
            for(char coluna : linha){
                if(coluna=='X')
                    tiros++;
            }
        }
        if(tiros==20)
            return true; 
        else
            return false;
    }
}
