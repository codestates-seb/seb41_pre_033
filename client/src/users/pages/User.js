import './user.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPen, faLocationDot } from "@fortawesome/free-solid-svg-icons";
import { faGithub } from "@fortawesome/free-brands-svg-icons";

//개별 유저의 페이지
function User () {
    return (
    <div id="user-body">
        <div id="user-header">
            <div id="user-pfp">A</div>
            <div id="user-text">
                <div id="user-name">A Name</div>
                <div id="user-info">
                    <div className='user-info-item'><FontAwesomeIcon className="user-icon" icon={faGithub} /></div>
                    <div className='user-info-item'><FontAwesomeIcon className="user-icon" icon={faLocationDot} /> From 맛동산</div>
                </div>
                <div className='user-info-item'>Title here</div>
            </div>
            <div id="edit-button-container">
                <button id="edit-profile"><FontAwesomeIcon className="user-icon" icon={faPen} />Edit profile</button>
            </div>
        </div>
        <div id="user-menu">
            <div className='user-menu-item'>Profile</div>
            <div className='user-menu-item'>Activity</div>
            <div className='user-menu-item'>Saves</div>
            <div className='user-menu-item'>Settings</div>
        </div>
    </div>
    )

}

export default User;