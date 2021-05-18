/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generalAccessPackage.databaseSubSystem;

import generalAccessPackage.classSubSystem.Address;
import generalAccessPackage.classSubSystem.Person;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.FormatterClosedException;

/**
 *
 * @author Diego Simões Maria, Italo Gustavo Donato Cordeiro, Marina Bernardes Diniz, Matheus Thiago de Souza Ferreira e Rosane Silva Freitas Araujo
 */
public class DatabaseAccess {

    //CONSTRUCTOR
    public DatabaseAccess() {
        
    }
    
    //METHODS
    public void readFile(ArrayList<Person> listaDeUsuarios){//Extrutura a lista de usuarios existente no banco de dados
        checkFile();
        FileReader fileForReader = null;
        BufferedReader infoForReader = null;
        String fullLine;
        String[] splitLine = null;
        try {
            fileForReader = new FileReader("src/generalAccessPackage/databaseSubSystem/bd_Users.txt");
            infoForReader = new BufferedReader(fileForReader);
        } catch(IOException ioException){
            System.err.println("Erro ao Abrir Arquivo.");
            System.exit(1);
        }
        
        try {
            fullLine = infoForReader.readLine();
            while(fullLine!= null){
                splitLine = fullLine.split(";");
                String[] localizacao = splitLine[8].split(":");
                String[] hobbies = splitLine[6].split(":");
                ArrayList<String> listaHobbies = new ArrayList<>();
                for(int i = 0; i < hobbies.length; i++){
                    listaHobbies.add(hobbies[i]);
                }
                listaDeUsuarios.add(new Person(splitLine[0], splitLine[1], splitLine[2], splitLine[3], splitLine[4], splitLine[5], listaHobbies, splitLine[7], new Address(localizacao[0], localizacao[1], localizacao[2], localizacao[3])));
                fullLine = infoForReader.readLine();
            }
        } catch (IOException ex) {
            System.err.println("Impossivel ler o Arquvio.");
            System.exit(1);
        } 
        
        try {
            fileForReader.close();
        } catch (IOException ex) {
            System.err.println("Arquivo não pode ser fechado corretamente!");
        }
    }
    public void writeFile(ArrayList<Person> listaDeUsuarios){//Salva a lista de usuarios no banco de dados
        FileWriter fileForSave = null;
        PrintWriter infoForSave = null;
        try{
            fileForSave = new FileWriter("src/generalAccessPackage/databaseSubSystem/bd_Users.txt");
            infoForSave = new PrintWriter(fileForSave);
        }catch(IOException ioException){
            System.err.println("Erro ao Abrir Arquivo.");
            System.exit(1);
        }
        
        for(int i = 0; i < listaDeUsuarios.size(); i++){
            try{
                String hobbies = listaDeUsuarios.get(i).getHobbies().get(0);
                String localizacao = listaDeUsuarios.get(i).getLocalizacao().getCidade() + ":" + listaDeUsuarios.get(i).getLocalizacao().getEstado() + ":" + listaDeUsuarios.get(i).getLocalizacao().getPais() + ":" + listaDeUsuarios.get(i).getLocalizacao().getProximidade();
                for(int j = 1; j < listaDeUsuarios.get(i).getHobbies().size(); j++){
                    hobbies = hobbies + ":" + listaDeUsuarios.get(i).getHobbies().get(j);
                }  
                infoForSave.printf("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", listaDeUsuarios.get(i).getId(), listaDeUsuarios.get(i).getNome(), listaDeUsuarios.get(i).getIdade(), listaDeUsuarios.get(i).getSexo(), listaDeUsuarios.get(i).getTelefone(), listaDeUsuarios.get(i).getSexoDePreferencia(), hobbies, listaDeUsuarios.get(i).getEscolaridade(), localizacao);
            }catch(FormatterClosedException formatterClosedException){
                System.err.println("Erro ao Gravar em Arquivo.");
                break;
            }
        }
        try {
            fileForSave.close();
        } catch (IOException ex) {
            System.err.println("Arquivo não pode ser fechado corretamente!");
        }
    }
    private void checkFile() {
        File file = new File("src/generalAccessPackage/databaseSubSystem/bd_Users.txt");
        try {
            file.createNewFile();
        } catch (IOException ex1) {
            System.out.println("Não foi possivel criar o arquivo.");
            System.exit(1);
        }
    }
}
