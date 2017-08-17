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
                <form className="form" action="/upload" method="POST" encType="multipart/form-data">
                    <div className="panel panel-default file-upload-panel">
                      <div className="panel-body">
                          <div className="form-group">
                              <label htmlFor="file-upload">Image upload: </label>
                              <input className="form-control file-upload" type="file" name="file-upload" id="file-upload"/>
                              <span>File size limited to less than 1MB!</span>
                          </div>
                          <div className="form-group">
                              <label htmlFor="num-of-images">Number of images to print: </label>
                              <select className="form-control num-of-images" name="num-of-images" id="num-of-images">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                              </select>
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