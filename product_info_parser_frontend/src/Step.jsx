import "./Step.css"

function Step({index, element}) {

    return (<>
        <div className="card-container">
            {element}
        </div>
    </>)
}

export default Step