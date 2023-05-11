/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;

/**
 *
 * @author abdelazizmezri
 */
public interface IService<T> {
    public void ajouter(T u);
    public void supprimer(int CIN);
    public void modifier(T u);
    public List<T> getAll();
}

