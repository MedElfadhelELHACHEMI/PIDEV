/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import com.models.entities.Cours;
import java.util.List;

/**
 *
 * @author haikal
 */
public interface IServiceFormateurs {
   List<Cours> getCoursesACCCoach(int idCoach);
}
