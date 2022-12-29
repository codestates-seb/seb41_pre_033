import React from 'react';
import { Link } from 'react-router-dom';
import './header.css'

function SearchDropDown () {

    return (
        <div id="search-dropdown">
        <div id="guide">
        <div className='guide-part one'>
            <div className='item'><span className='darkgray'>[tag]</span><span className='lightgray'>search within a tag</span></div>
            <div className='item'><span className='darkgray'>user:1234</span><span className='lightgray'>search by author</span></div>
            <div className='item'><span className='darkgray'>"words here"</span><span className='lightgray'>exact phrase</span></div>
            <div className='item'><span className='darkgray'>collective: "Name"</span><span className='lightgray'>collective content</span></div>
        </div>
        <div className='guide-part two'>
            <div className='item'><span className='darkgray'>answers:0</span><span className='lightgray'>unanswered questions</span></div>
            <div className='item'><span className='darkgray'>score:3</span><span className='lightgray'>posts with a 3+ score</span></div>
            <div className='item'><span className='darkgray'>is:question</span><span className='lightgray'>type of post</span></div>
            <div className='item'><span className='darkgray'>isaccepted:yes</span><span className='lightgray'>search within status</span></div>
        </div>
        </div>
        <div className='dropdown-footer'>
            <button className='question-button'>Ask a question</button>
            <span className='search-help'><Link to="/search/help" id="search-link">Search help</Link></span>
        </div>
      </div>
    )
}


export default SearchDropDown;