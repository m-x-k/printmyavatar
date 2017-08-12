'use strict';

const React = require('react');
const ReactDOM = require('react-dom');

class PrintMyAvatar extends React.Component {
    render() {
        return(
            <div id="container" className="container">
                <object className="mainLogo center" type="image/svg+xml" data="/images/logo.svg">
                  Your browser does not support SVG
                </object>
                <div className="row">
                    <button className="btn btn-primary btn-lg upload-btn center">Upload + Build + Print</button>
                </div>
                <footer>
                    <img className="agileLogo" src="/images/agile.svg"/>
                    <img className="freeLogo" src="/images/free.svg"/>
                </footer>
            </div>
        );
    }
}

ReactDOM.render(
    <PrintMyAvatar />,
    document.getElementById("react")
);