import React, {useState} from "react";



const ModalAddDialog = ({handleClose}) => {

    const [title, setTitle] = useState("")
    const [story, setStory] = useState("")

    interface FormDataType {title:string, story: string}
    const responseBody: FormDataType = {title: "", story: ""}

    const onSubmitHandler = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        responseBody.title = title
        responseBody.story = story
        console.log(JSON.stringify(responseBody))
        //Form submission happens here
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(responseBody)
        };
        fetch('http://localhost:8282/restapi/story', requestOptions)
            .then(response => response.json())
            .then(data => {
                console.log("successfully added.")
                handleClose();
            });
    }

    const inputChangeHandler = (setFunction: React.Dispatch<React.SetStateAction<string>>, event: React.ChangeEvent<HTMLInputElement>) => {
        setFunction(event.target.value)
    }


    return (
        <div className="popup-box">
            <div className="box">
                <span className="close-icon" onClick={handleClose}>x</span>
                {/*Here is our content loaded from the html fetched page*/}
                <div>
                    <form onSubmit={onSubmitHandler}>
                        <div><label htmlFor="title">Title</label></div>
                        <div><input id="title" onChange={(e)=>inputChangeHandler(setTitle, e)} type="text"/></div>
                        <br/>
                        <div><label htmlFor="story">Story</label></div>
                        <div><textarea id="story" onChange={(e)=>inputChangeHandler(setStory, e)} type="text"/></div>
                        <br/>
                        <input className={"button-49"} type="submit"/>
                    </form>
                </div>

            </div>
        </div>
    );
};

export default ModalAddDialog;