/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generalAccessPackage.classSubSystem;


/**
 *
 * @author Diego Simões Maria, Italo Gustavo Donato Cordeiro, Marina Bernardes Diniz, Matheus Thiago de Souza Ferreira e Rosane Silva Freitas Araujo
 */
public class Address {
    private String cidade;
    private String estado;
    private String pais;
    private String proximidade;
    
    //CONSTRUCTOR
    public Address(String cidade, String estado, String pais, String proximidade) {
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.proximidade = proximidade;
    }
    
    //GETTER
    public String getCidade() {
        return cidade;
    }
    public String getEstado() {
        return estado;
    }
    public String getPais() {
        return pais;
    }
    public String getProximidade() {
        return proximidade;
    }
     
    //SETTER
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public void setProximidade(String proximidade) {
        this.proximidade = proximidade;
    }
    
}
