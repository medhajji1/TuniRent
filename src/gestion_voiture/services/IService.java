/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_voiture.services;

import java.util.List;

/**
 *
 * @author moham
 */
public interface IService <T, ID>{
    public void ajouter(T p);
    public void supprimer(ID id);
    public void modifier(T p);
    public List<T> tout();
    public T one(ID id);
}
