/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generalAccessPackage.classSubSystem;

import java.util.ArrayList;

/**
 *
 * @author Diego Simões Maria, Italo Gustavo Donato Cordeiro, Marina Bernardes Diniz, Matheus Thiago de Souza Ferreira e Rosane Silva Freitas Araujo
 */
public class Person {
    private String id;
    private String nome;
    private String idade;
    private String sexo;
    private String telefone;
    private String sexoDePreferencia;
    private ArrayList<String> hobbies;
    private String escolaridade;
    private Address localizacao;
    
    //CONSTRUCTOR
    public Person(String id, String nome, String idade, String sexo, String telefone, String sexoDePreferencia, ArrayList<String> hobbies, String escolaridade, Address localizacao) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.telefone = telefone;
        this.sexoDePreferencia = sexoDePreferencia;
        this.hobbies = hobbies;
        this.escolaridade = escolaridade;
        this.localizacao = localizacao;
    }
    
    //GETTER
    public String getId(){
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getIdade() {
        return idade;
    }
    public String getSexo() {
        return sexo;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getSexoDePreferencia() {
        return sexoDePreferencia;
    }
    public ArrayList<String> getHobbies() {
        return hobbies;
    }
    public String getEscolaridade() {
        return escolaridade;
    }
    public Address getLocalizacao() {
        return localizacao;
    }
    
    //SETTER
    public void setId(String id){
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setIdade(String idade) {
        this.idade = idade;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setSexoDePreferencia(String sexoDePreferencia) {
        this.sexoDePreferencia = sexoDePreferencia;
    }
    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }
    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }
    public void setLocalizacao(Address localizacao) {
        this.localizacao = localizacao;
    }
}
