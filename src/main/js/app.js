'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
import ReCAPTCHA from 'react-google-recaptcha';

import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom'

class AppRouter extends React.Component {
    render() {
        return(
          <Router>
            <div>
              <ul className="nav nav-pills nav-stacked col-xs-1">
                <li role="presentation"><Link to="/">Home</Link></li>
                <li role="presentation"><Link to="/about">About</Link></li>
              </ul>

              <Route exact path="/" component={PrintMyAvatar}/>
              <Route path="/about" component={About}/>
            </div>
          </Router>
      );
   }
}

class CreatedBy extends React.Component {
    render() {
        return(
            <footer>
                <img className="agileLogo" src="/images/agile.svg"/>
                <img className="freeLogo" src="/images/free.svg"/>
                <div className="center">
                    <p>Created by <a href="http://mxk.solutions">MXK Solutions Ltd</a>.</p>
                </div>
            </footer>
        );
    }
}

class About extends React.Component {
    render() {
        return(
            <div id="about-page-content">
                <h1>About</h1>
                <p>This application was created to help with kanban style boards where team members are typically identified using avatars.</p>
                <p>The idea was to allow users to upload a chosen avatar and then provide a web page representation for printing.</p>
                <p>The avatar print outs can then be combined with magnetic strips.</p>
            </div>
        );
    }
}

class AppMain extends React.Component {
    render() {
        return(
            <div id="container" className="container">
                <object className="mainLogo center" type="image/svg+xml" data="/images/logo.svg">
                  Your browser does not support SVG
                </object>
                <AppRouter />
                <CreatedBy/>
            </div>
        );
    }
}

class PrintMyAvatar extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            'gRecaptchaResponse': ''
        }
    }

    onChange(response) {
        this.setState({
           'gRecaptchaResponse': response
        });
    }

    render() {
        return(
            <div id="print-my-avatar-form">
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
                                    <option value="9">9</option>
                              </select>
                          </div>
                      </div>
                      <div className="form-group center">
                          <ReCAPTCHA ref="recaptcha" sitekey="6Lcsfy0UAAAAAJKU10AcIwSOMc879KcKF2-qZLzQ"
                                     onChange={this.onChange.bind(this)}/>
                      </div>
                      <div className="form-group">
                          <button className="btn btn-primary btn-lg upload-btn center">Upload + Build + Display</button>
                      </div>
                    </div>
                </form>
            </div>
        );
    }
}

ReactDOM.render(
    <AppMain />,
    document.getElementById("react")
);