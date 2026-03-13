package br.course.elite.persistence;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Pessoa extends PanacheEntity {

    public String nome; // Se definir como public, o prórpio panache generia os campos
    public Integer idade;

    public static List<Pessoa> findByAno(Integer idade) {
        return find("idade", idade).list();
    }
}
