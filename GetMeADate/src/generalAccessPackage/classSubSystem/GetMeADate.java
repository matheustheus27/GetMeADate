/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generalAccessPackage.classSubSystem;

import generalAccessPackage.databaseSubSystem.DatabaseAccess;
import java.util.ArrayList;

/**
 *
 * @author Diego Simões Maria, Italo Gustavo Donato Cordeiro, Marina Bernardes Diniz, Matheus Thiago de Souza Ferreira e Rosane Silva Freitas Araujo
 */
public class GetMeADate {

    private ArrayList<Person> listaDeUsuarios;
    private DatabaseAccess fileAccess;

    //CONSTRUCTOR
    public GetMeADate() {
        this.fileAccess = new DatabaseAccess();
        this.listaDeUsuarios = new ArrayList<>();
        conectUserDB();
    }

    //GETTER
    public ArrayList<Person> getListaDeUsuarios() {
        return listaDeUsuarios;
    }
    public DatabaseAccess getFileAccess() {
        return fileAccess;
    }

    //SETTER
    public void setListaDeUsuarios(ArrayList<Person> listaDeUsuarios) {
        this.listaDeUsuarios = listaDeUsuarios;
    }
    public void setFileAccess(DatabaseAccess fileAccess) {
        this.fileAccess = fileAccess;
    }

    //METHODS
    private void conectUserDB() {//Busca as informações já existentes no banco de dados
        this.fileAccess.readFile(this.listaDeUsuarios);
    }
    public void saveUserDB() {//Salva a ArrayList no banco de dados
        this.fileAccess.writeFile(this.listaDeUsuarios);
    }
    public ArrayList searchMatch(Person perfil) {//Procura perfis compativeis
        ArrayList<Person> mathsPossiveis = new ArrayList<>();
        for (int cont = 0; cont < this.listaDeUsuarios.size(); cont++) {
            if (!perfil.getId().contains(this.listaDeUsuarios.get(cont).getId())) {//Verifica se os IDs são diferentes
                if (perfil.getIdade().equals(this.listaDeUsuarios.get(cont).getIdade())) {//Verifica se as idades são compativeis
                    boolean podeSeguir = false;
                    switch(perfil.getLocalizacao().getProximidade()){//Verifica se o perfil está de acordo com o alcance escolhido
                        case "Mesma Cidade":
                            if(perfil.getLocalizacao().getCidade().contains(this.listaDeUsuarios.get(cont).getLocalizacao().getCidade())){
                                podeSeguir = true;
                            }
                        break;
                        case "Mesmo Estado":
                            if(perfil.getLocalizacao().getEstado().equals(this.listaDeUsuarios.get(cont).getLocalizacao().getEstado())){
                                podeSeguir = true;
                            }
                        break;
                        case "Mesmo País":
                            if(perfil.getLocalizacao().getPais().equals(this.listaDeUsuarios.get(cont).getLocalizacao().getPais())){
                                podeSeguir = true;
                            }
                        break;
                        case "Qualquer Lugar":
                            podeSeguir = true;
                        break;
                    }
                    if (podeSeguir == true) {//Caso o perfil atenda o requisito de proximidade pode seguir adiante
                        int qtdHobbies = 0;

                        for (int i = 0; i < perfil.getHobbies().size(); i++) {//Verifica a quantidade de Hobbies em comum
                            for (int j = 0; j < this.listaDeUsuarios.get(cont).getHobbies().size(); j++) {
                                if (perfil.getHobbies().get(i).equals(this.listaDeUsuarios.get(cont).getHobbies().get(j))) {
                                    qtdHobbies++;
                                }
                            }
                        }
                        
                        if (qtdHobbies > 3) {//Caso a quantidade de hobbies em comum atenda os requisitos minimos (4 hobbies), pode prosseguir
                            switch (perfil.getSexo()) {//Verifica o sexo do usuario
                                case "Heterossexual - H":
                                    switch (perfil.getSexoDePreferencia()) {//Verifica o sexo de preferencia e se o possivel Match atende os requisitos
                                        case "Mulher":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Lesbica") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Homem":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Gay") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem")|| this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Ambos":
                                            if ((this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                    }
                                break;
                                case "Heterossexual - M":
                                    switch (perfil.getSexoDePreferencia()) {
                                        case "Mulher":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Lesbica") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Homem":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Gay") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Ambos":
                                            if ((this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                    }
                                break;
                                case "Gay":
                                    switch (perfil.getSexoDePreferencia()) {
                                        case "Mulher":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Lesbica") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Homem":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Gay") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem")|| this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Ambos":
                                            if ((this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                    }
                                break;
                                case "Lesbica":
                                    switch (perfil.getSexoDePreferencia()) {
                                        case "Mulher":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Lesbica") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Homem":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Gay") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Ambos":
                                            if ((this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                    }
                                break;
                                case "Bissexual - H":
                                    switch (perfil.getSexoDePreferencia()) {
                                        case "Mulher":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Lesbica") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Homem":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Gay") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem")|| this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Ambos":
                                            if ((this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                    }
                                break;
                                case "Bissexual - M":
                                    switch (perfil.getSexoDePreferencia()) {
                                        case "Mulher":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Lesbica") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Homem":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Gay") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Ambos":
                                            if ((this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                    }
                                break;
                                case "Trans - H":
                                    switch (perfil.getSexoDePreferencia()) {
                                        case "Mulher":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Lesbica") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Homem":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Gay") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem")|| this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Ambos":
                                            if ((this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Homem") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                    }
                                break;
                                case "Trans - M":
                                    switch (perfil.getSexoDePreferencia()) {
                                        case "Mulher":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Lesbica") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - M") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Homem":
                                            if ((this.listaDeUsuarios.get(cont).getSexo().contains("Heterossexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Gay") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Bissexual - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) || (this.listaDeUsuarios.get(cont).getSexo().contains("Trans - H") && (this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos")))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                        case "Ambos":
                                            if ((this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Mulher") || this.listaDeUsuarios.get(cont).getSexoDePreferencia().contains("Ambos"))) {
                                                mathsPossiveis.add(this.listaDeUsuarios.get(cont));
                                            }
                                        break;
                                    }
                            }
                        }
                    }
                }
            }
        }
        
        return mathsPossiveis;//Após verificar toda a lista de usuarios, é retornado a lista daqueles que preencheram todos os requisitos
    }
    public Person searchUser(String id){//Busca o usuario ao qual o ID pertence
        Person aux = null;
        for(int i = 0; i < this.listaDeUsuarios.size(); i++){
            if(this.listaDeUsuarios.get(i).getId().equals(id)){
                aux = this.listaDeUsuarios.get(i);
            }
        }
        
        return aux;
    }
    public boolean removerUser(Person user){//Busca e remove o usuario caso ele exista dentro da lista
        for(int i = 0; i < this.listaDeUsuarios.size(); i++){
            if(this.listaDeUsuarios.get(i).getId().equals(user.getId())){
                this.listaDeUsuarios.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean alterarUser(Person user){//Adiciona as novas informações de um usuario existente
        for(int i = 0; i < this.listaDeUsuarios.size(); i++){
            if(this.listaDeUsuarios.get(i).getId().equals(user.getId())){
                this.listaDeUsuarios.set(i, user);
                return true;
            }
        }
       return false;
    }
}
