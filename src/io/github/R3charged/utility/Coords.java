package io.github.R3charged.utility;

import java.util.Objects;

public class Coords {
    private int x,z;
    public Coords(int x,int z){
        this.x=x;
        this.z=z;
    }
    public int hashCode(){
        return Objects.hash(x,z);
    }
    public boolean equals(Object obj){
        return hashCode()==obj.hashCode();
    }
}
