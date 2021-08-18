import java.util.Arrays;
/* An ArrayQueue is a queue based on an array. The array is a circular array.
 * The queue should grow dynamically if it gets full (it should double in
 * capacity each time it gets full).
 *
 * The default constructor creates an ArrayQueue that is empty but has some
 * capacity > 0. The copy constructor creates a deep copy of the given
 * ArrayQueue object. This means that it gets its own deep copy of the data.
 *
 * The add method adds an element to the back of the queue. The remove method
 * removes one item from the front of the queue. These methods should not move
 * any data already in the queue. The getFront method returns the item at the
 * front of the queue.
 *
 * The getLength function returns the length of the queue. If the length is 0,
 * the queue is considered to be empty.
 *
 * The copyFrom method first checks to see if the queue we are assigning to is
 * the same as this, and if not, makes a deep copy of the given queue.
 *
 * The doubleCapacity method doubles the capacity of the ArrayQueue, and updates
 * the data members so they are now valid for the newly allocated array.
 *
 * Note that even if some methods are not used in your project, you still need
 * to implement them all correctly!
 */

class ArrayQueue {
  private Location[] data;
  private int length, capacity, front;
  
  private void doubleCapacity() {
    // -
    Location[] proxyData = new Location[capacity*2];
    for (int j = 0; j < length; j++) {
      proxyData[j] = data[(front + j) % capacity];
    }
    data = proxyData;
    capacity *= 2;
    front = 0;
  }

  ArrayQueue() {
    // Constructor
    data = new Location[1];
    length = 0;
    capacity = 1;
    front = 0;
  }
  
  ArrayQueue(ArrayQueue q) {
    // -
    capacity = q.capacity;
    front = q.front;
    length = q.length;
    data = new Location[capacity];
    for (int i = 0; i < capacity; i++) {
      this.data[i] = q.data[i];
    }
  }

  void add(Location loc) {
    // -
    if (length == capacity) {
      this.doubleCapacity();
    }
    data[(length + front) % capacity] = loc;
    length++;
  }

  void remove() {
    // -
    front = (front +1) % capacity;
    length--;
  }

  Location getFront() {
    // returns the location at the front of the queue
    return data[front];
  }

  int getLength()  {
    // returns length
    return length;
  }

  ArrayQueue copyFrom(ArrayQueue q) {
  // -
    if (!(this == q)) {
      capacity = q.capacity;
      length = q.length;
      front = q.front;
      data = new Location[capacity];
      for (int y = 0; y < capacity; y++) {
        this.data[y] = q.data[y];
      }
    }
    return this;
  }
  
  @Override
  public String toString() {
    return "ArrayQueue{" + "data=" + Arrays.toString(data) +
      ", length=" + length +
      ", capacity=" + capacity +
      ", front=" + front +
    "}";
  }
}
