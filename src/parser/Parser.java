/*
 * Copyright (C) 2017 admin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package parser;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Parser {
    
    public String[] parse(String input,ArrayList<ArrayList <String>> arguments){
        //L'output tindra com a minim el nombre d'arguments donats
        String output[]=new String[arguments.size()];
        
        //rep un input, i el separa amb els diferents arguments
        //cada argument es un camp del nostr formulari, que conte els posibles 
        //regex que compleixen aquest camp
        //Per cada argument (link, gestio, resum, contacte, seguent)
        int aux=0;
        arguments.forEach((argument) -> {
            //mirem si trobem en l'input el text seleccionat.
            argument.forEach((String regex) -> {
                String[] result=input.split("\n");
                output[aux]=String.valueOf(result[aux]);
                aux++;
                System.out.println(regex);
            });
            System.out.println("Fi darray");
        });
        
        return output;
    } 
    
    private String[] separate (String input, String regex){
        String[] output= null;
        
        return output;
    }
}
