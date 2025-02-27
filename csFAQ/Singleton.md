# Singleton in Java

```java
public class Singleton {
    private static volatile Singleton instance;
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

# Singleton in python


## **1️⃣ Classic Singleton Using `__new__` (Recommended)**
The `__new__` method ensures only **one instance** is created.
```python
class Singleton:
    _instance = None  # Class-level variable to store the instance
    
    def __new__(cls, *args, **kwargs):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
        return cls._instance

# Usage
s1 = Singleton()
s2 = Singleton()

print(s1 is s2)  # ✅ True (Both are the same instance)
```
✅ **Best for thread-safety & flexibility**

---

## **2️⃣ Singleton Using a Decorator**
You can use a **decorator** to enforce the Singleton pattern.
```python
def singleton(cls):
    instances = {}

    def get_instance(*args, **kwargs):
        if cls not in instances:
            instances[cls] = cls(*args, **kwargs)
        return instances[cls]
    
    return get_instance

@singleton
class Singleton:
    pass

# Usage
s1 = Singleton()
s2 = Singleton()
print(s1 is s2)  # ✅ True
```
✅ **Useful when applying Singleton to multiple classes**

---

## **3️⃣ Singleton Using a Metaclass**
A **metaclass** controls class creation and ensures only **one instance exists**.
```python
class SingletonMeta(type):
    _instances = {}

    def __call__(cls, *args, **kwargs):
        if cls not in cls._instances:
            cls._instances[cls] = super().__call__(*args, **kwargs)
        return cls._instances[cls]

class Singleton(metaclass=SingletonMeta):
    pass

# Usage
s1 = Singleton()
s2 = Singleton()
print(s1 is s2)  # ✅ True
```
✅ **Best for advanced use cases (e.g., frameworks)**

---

## **4️⃣ Singleton Using a Module (Simplest Approach)**
Since **modules are singletons by default**, you can use a module instead of a class.

🔹 **singleton_module.py**
```python
class Singleton:
    pass
singleton_instance = Singleton()
```
🔹 **Usage in another file**
```python
from singleton_module import singleton_instance

s1 = singleton_instance
s2 = singleton_instance

print(s1 is s2)  # ✅ True
```
✅ **Simple and Pythonic**

---

## **🚀 Best Choice?**
| Method | Pros | Cons |
|--------|------|------|
| `__new__` | Simple, thread-safe | Slightly non-Pythonic |
| Decorator | Reusable for multiple classes | Harder to extend |
| Metaclass | Clean for multiple singletons | Complex for beginners |
| Module | Pythonic, very simple | No control over instantiation |

🔹 **For simple cases:** **Use a module**  
🔹 **For more control:** **Use `__new__` or metaclasses**  

Note: The `pass` statement in Python is a placeholder that does nothing.
