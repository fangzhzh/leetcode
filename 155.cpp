class MinStack {
    vector<int> elements;
    map<int, int> mapping;
public:
    /** initialize your data structure here. */
    MinStack() {
        
    }
    
    void push(int x) {
        int& value = mapping[x];
        value += 1;
        elements.push_back(x);
    }
    
    void pop() {
        int x= top();
        int& value = mapping[x];
        value -= 1;
        if(value == 0){
            mapping.erase(x);
        }
        elements.pop_back();
    }
    
    int top() {
        return elements.back();
    }
    
    int getMin() {
        return mapping.begin()->first;
    }
};

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
