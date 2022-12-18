import React, { useState, useEffect } from "react";
import 'reactjs-popup/dist/index.css';
import "./App.css";
import ElementsList from "./components/ElementsList";
import ModalAddDialog from "./components/ModalAddDialog";



const App = () => {
  const [isPending,setIsPending] = useState(true);
  const [elements, setElements] = useState([]);
  const [loadedElements,setLoadedElements] = useState([]);
  const [isOpen, setIsOpen] = useState(false);
  const [addIsOpen, setAddIsOpen] = useState(false);
  const [story, setStory] = useState("");


  const togglePopup = (id) => {
      //in order to fetch dynamic urls pass in the id to the fetch input url.
      fetch('http://localhost:8282/restapi/stories/'+id)
          .then(data => {
              return data.json();
          })
          .then(element => {
              setStory(element);
              setIsOpen(!isOpen);
      });
  }
    const toggleClose = () => {
                setIsOpen(!isOpen);
    }

    const toggleAdd = () => {
          setAddIsOpen(!addIsOpen);
    }

  useEffect(() => {
    // fetch data
    fetch('http://localhost:8282/restapi/blogs')
        .then(data => {
          return data.json();
        })
        .then(elements => {
          setElements(elements);
          setLoadedElements(elements);
          setIsPending(false);
        });
      // set state when the data received
  }, [addIsOpen,isOpen]);

  const handleChange = (e)=>{

      if(e.target.value === ""){
            setLoadedElements(elements);
      }
      else {
          let t;
          const newEl = elements.filter((element) => {
              t = element.title;
              return t.includes(e.target.value)
          });
          setLoadedElements(newEl);
      }
  }

  return (
    <div className="App">
      <div className="container">
          <input type="text" className="button-56" onChange={handleChange} placeholder="Search..."/>
              <div className="search"></div>
        <h1>Story Titles</h1>
          {isPending && <h1>Loading your data...</h1>}
          {!isPending && <ElementsList
          elements={loadedElements}
          togglePopup ={togglePopup}
          toggleClose ={toggleClose}
          isOpen = {isOpen}
          story = {story}
        />}
          {addIsOpen && <ModalAddDialog
              handleClose={toggleAdd}
          />}
          <br/>
          <input
              className="button-74"
              type="button"
              value="Add Story"
              onClick={toggleAdd}
          />
      </div>
    </div>
  );
};

export default App;
