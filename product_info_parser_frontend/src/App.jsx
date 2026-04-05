
import { useState } from 'react'
import './App.css'
import Step from './Step'
function App() {
  const [task, setTask] = useState("");
  const [list, setList] = useState([]);

  const submitTask = async () => {
    
    try {
      const response = await fetch("http://localhost:8080/api",{
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
          request : task
        })
      })
      const data = await response.json();
      console.log(Object.values(data));
      setList(Object.values(data));
    } catch (error) {
      console.error(error)
    }
  }

  return (
    <>
      <div className='container'>
        <div className='input'>
          <h1 className='text'>
            What Task to Break Down?
          </h1>
          <input 
                 type="text" 
                 placeholder='Enter Task'
                 onChange={(e) => setTask(e.target.value)}/>
          <button className='send-button'
                  onClick={() => submitTask()}>Enter</button>
        </div>
        <div className='list'>
            {list.map((item, index) => (
              <Step key={index}
                    index={index}
                    element={item}></Step>
            ))}
        </div>
      </div>
    </>
  )
}

export default App
