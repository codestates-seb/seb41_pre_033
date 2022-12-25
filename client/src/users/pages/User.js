import './user.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPen, faLocationDot } from "@fortawesome/free-solid-svg-icons";
import { faGithub } from "@fortawesome/free-brands-svg-icons";
import { useParams } from 'react-router-dom';

//개별 유저의 페이지
const User = ({users}) => {
    const { id, user_nickname } = useParams();
    const test = id;
    console.log(test);
    const owner = users.filter(e =>  e.id === test);
    console.log("users is ", users);
    console.log(owner);
    return (
    <div id="user-body">
        <div id="user-header">
            <div id="user-pfp">{`${(user_nickname).slice(0,1).toUpperCase()}`}</div>
            <div id="user-text">
                <div id="user-name">{user_nickname}</div>
                <div id="user-info">
                    <div className='user-info-item'><FontAwesomeIcon className="user-icon" icon={faGithub} /></div>
                    <div className='user-info-item'><FontAwesomeIcon className="user-icon" icon={faLocationDot} /> From {owner.user_country}</div>
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