import './App.css';
import { useState } from 'react';

function App() {

  const [counter, setCounter] = useState(0);

  const incrementCounter = () => {
    setCounter(counter + 1);
  }

  return (
    <div className='blue'>
      <h1>Hello World!</h1>
      <p>Welcome to my amazing React app!</p>
      <hr/>
      <p>The current value of the counter is {counter}</p>
      <button onClick={incrementCounter}>Increment Counter</button>
    </div>
  );
}

export default App;
