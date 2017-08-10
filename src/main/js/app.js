'use strict';

const React = require('react');
const ReactDOM = require('react-dom');

class PrintMyAvatar extends React.Component {
    render() {
        return(
            <div>
                <p>HELLO MARTIN</p>
                <button className="btn btn-primary">Test</button>
            </div>
        );
    }
}

ReactDOM.render(
    <PrintMyAvatar />,
    document.getElementById("react")
);