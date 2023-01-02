import { Link, useNavigate, useParams } from "react-router-dom";
import { useState } from 'react';
import axios from 'axios';
import './edituser.css';

function EditUser () {
    const { id } = useParams();
    const navigate = useNavigate();
    const [formValue, setformValue] = useState({
        country: "국가",
        introduction: "자기소개",
        link: "개인 사이트 링크",
        nickname: "닉네임",
        title: "소제목",
        userTags: [
          {
            tagName: "c"
          },
          {
            tagName: "java"
          }
        ]
      });
    const handleChange = (event) => {
        setformValue({
      ...formValue,
      [event.target.name]: event.target.value
    });
  }

  const goBack = () => {
    navigate(`/users/${id}`)
  }
  const patchUser = (e) => {
    e.preventDefault();
    console.log("tag ", formValue.userTags)
    axios.patch(`http://ec2-15-164-87-251.ap-northeast-2.compute.amazonaws.com:8080/users/edit/${id}`, formValue)
    .then((response) => {
      console.log("res is ", response);
      if (response.status===200) {
        alert("Changed info successfully!");
        navigate(`/users/${id}`);
      }
    })
    .catch((err) => {
      if (err.response.status===400){
        alert("example")
      }
      console.log("err is ", err);
    });
  };
    return (
        <div>
            <div>edit</div>
            <div>
                <form id="edit-form">
                <label for="nickname">Display name</label>
                <input name="nickname" type="text" id="nickname-input" onChange={handleChange}/>
                <label for="country">Location</label>
                <input name="country" type="text" id="country-input" onChange={handleChange}/>
                <label for="title">Title</label>
                <input name="title" type="text" id="title-input" onChange={handleChange}/>
                <label for="introduction">About me</label>
                <input name="introduction" type="text" id="introduction-input" onChange={handleChange}/>
                <label for="link">Link</label>
                <input name="link" type="text" id="link-input" onChange={handleChange}/>
                <button id="edit-page-button" onClick={patchUser} type="submit">Save</button>
                <button id="cancel" onClick={goBack}>Cancel</button>
                </form>
            </div>
        </div>
    );
}

export default EditUser