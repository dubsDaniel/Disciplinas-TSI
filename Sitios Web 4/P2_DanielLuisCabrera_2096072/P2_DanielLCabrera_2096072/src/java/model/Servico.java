package model;

import java.io.Serializable;
import javax.persistence.Id;

/**
  * @author dudbub
  * Classe para listar os tipos de serviços
  */
public class Servico implements Serializable { //Classe para gerar a lista de serviços e controlar pelo ID
    @Id //anotação Id passado nas últimas aulas
    private int id;
    private String desc;

    public Servico() {} //Construtor vazio

    public Servico(int id, String desc) { //Construtor com os atributos
        this.id = id;
        this.desc = desc;
    }
    //getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    //hash code e equals conforme passado em aula
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Servico other = (Servico) obj;
        return this.id == other.id;
    }
}
