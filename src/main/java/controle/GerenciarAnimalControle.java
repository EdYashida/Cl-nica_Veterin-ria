package controle;

import dao.Dao;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Animal;
import modelo.Consulta;
import modelo.Tutor;
import modelo.Usuario;
import util.JsfUtil;

/**
 *
 * @author José
 */
@Named
@ViewScoped
public class GerenciarAnimalControle implements Serializable {
    private Dao<Tutor> daoTutor;
    private Dao<Animal> daoAnimalAnimal;
    private List<Tutor> listaTutores;
    private List<Animal> listaAnimais;
    private Animal animal; 
    
    @PostConstruct
    public void iniciar() {
        daoTutor = new Dao(Tutor.class);
        daoAnimalAnimal = new Dao (Animal.class);
        listaTutores = daoTutor.listarTodos();
        animal = new Animal();
        listaAnimais = daoAnimalAnimal.listarTodos();
    }
    public void buscar(){
        animal = daoAnimalAnimal.buscarPorNome(animal.getNome());
    }

    public List<Animal> getListaAnimais() {
        return listaAnimais;
    }

    public void setListaAnimais(List<Animal> listaAnimais) {
        this.listaAnimais = listaAnimais;
    }

    public String salvar(){
        animal.getTutor().getAnimais().add(animal);
        daoTutor.alterar(animal.getTutor());
        animal = new Animal(); // limpa os campos 
        JsfUtil.mostrarSucesso("Animal cadastrado");
        listaTutores = daoTutor.listarTodos(); // atualiza tabela 
        return null;
        
    }
    public List<Tutor> getListaTutores() {
        return listaTutores;
    }

    public void setListaTutores(List<Tutor> listaTutores) {
        this.listaTutores = listaTutores;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
       
}