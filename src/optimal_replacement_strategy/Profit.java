package optimal_replacement_strategy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class Profit {
    private int _pr;
    private boolean save;
    
    public Profit(int _pr, boolean save){
        this._pr=_pr;
        this.save=save;
    }
    
    public int Pr(){
        return _pr;
    }
    public boolean Save(){
        return save;
    }
}
