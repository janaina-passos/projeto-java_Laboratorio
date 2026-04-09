public class Vetor <T>{
    
    T[] A; //armazena os elementos da lista
    int capacity; // capacidade da lista (o que pode ter)
    int size; // elementos na lista (o que tem no momento)
    
    public Vetor(int capacity){
        A = (T[]) new Object[capacity]; //flexibilidade para criar a lista (não é rígida)
        this.size = 0;
        this.capacity = capacity;
    }
    
    //verifica se o vetor está vazio
    public boolean isEmpty(){
        //uma operação no if -> não precisa de {}
        if (size == 0)
            return true;
        else
            return false;
    }
    
    //retorna a quantidade de elementos da lista
    public int size(){
        return size;
    }
    
    //retorna o elemento da posição i
    public T get(int i) throws Exception{
        if (i >= size)
            throw new Exception ("Posição Inválida!");
        return A[i];
    }
    
    //altera o conteúdo da posição i para n
    public void set(int i, T n) throws Exception{
        if (i >= size)
            throw new Exception ("Posição Inválida!");
        A[i] = n;
    }
    
    //insere novo elemento na lista na posição i
    public void add(int i, T n) throws Exception{
        if (size == capacity)  
            throw new Exception ("A lista está cheia!"); 
            
        if (i > size)
            throw new Exception ("Posição de inserção inválida!");
        
        for (int z = size; z > i; z--)
            A[i] = A[z-1];
        
        A[i] = n;
        size ++;
    }
    
    //remove o conteúdo da posição i
    public void remove(int i) throws Exception{
        if (isEmpty())
            throw new Exception ("A lista está vazia!");
        
        if (i >= size)
            throw new Exception ("Posição Inválida!");
        
        for (int z = i; z < size-1; z++)
            A[z] = A[z+1];
            
        size --;
    }
}
