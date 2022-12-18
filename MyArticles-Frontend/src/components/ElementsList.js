import ModalDialog from "./ModalDialog";




const ElementsList = ({ elements, togglePopup, isOpen, story, toggleClose }) => {

  return (
    <ul className="allElements">
      {elements.map((t) => (
        <li className="singleElement" key={t.id}>
          <span className="elementText">
            {t.title}
              {t.description}
          </span>
            <input
                className="button-54"
                type="button"
                value="Show Story"
                onClick={()=> togglePopup(t.id)}
            />
            {isOpen && <ModalDialog
                content={
                story
                }
                handleClose={toggleClose}
            />}
        </li>

      ))}
          </ul>
  );
};

export default ElementsList;
