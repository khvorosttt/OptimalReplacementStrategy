/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package optimal_replacement_strategy;

/**
 *
 * @author Lenovo
 */
public class ProfitPolitica {
    private Profit profit;
    private int age;
    
    public ProfitPolitica(Profit profit, int age){
        this.profit=profit;
        this.age=age;
    }
    
    public Profit _profit(){
        return profit;
    }
    public int Age(){
        return age;
    }
}
