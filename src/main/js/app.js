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
                <form className="form">
                    <div className="panel panel-default file-upload-panel">
                      <div className="panel-body">
                          <div className="form-group">
                              <label htmlFor="file-upload">Image upload: </label>
                              <input className="form-control file-upload" type="file" name="file-upload" id="file-upload"/>
                          </div>
                          <div className="form-group">
                              <label htmlFor="file-url">Image URL: </label>
                              <input className="form-control file-url" type="text" name="file-url" id="file-url"/>
                          </div>
                      </div>
                      <div className="form-group">
                          <button className="btn btn-primary btn-lg upload-btn center">Upload + Build + Print</button>
                      </div>
                    </div>
                </form>
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