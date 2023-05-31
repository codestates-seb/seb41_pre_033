import { Link, useNavigate, useParams } from "react-router-dom";
import { useState, useEffect } from 'react';
import Navbar from "../../components/navbar/Navbar";
import axios from 'axios';
import './edituser.css';

function EditUser () {
    const { id } = useParams();
    const navigate = useNavigate();
    const [former, setFormer] = useState({
      "userId": 1,
      "nickname": "loading...",
      "email": "loading...",
      "country": null,
      "reputation": 0,
      "title": null,
      "introduction": null,
      "link": null,
      "userTags": [],
      "questions": [],
      "answers": []
    });
    const [formValue, setformValue] = useState({
      country: "Korea",
      introduction: "I'm just a plain human",
      link: "github.com",
      nickname: "Anonymous",
      title: "No title yet!",
      userTags: [
        {
          tagName: "C++"
        },
        {
          tagName: "C#"
        }
      ]
    });

    useEffect(() => {
      const getFormer = () => {
          fetch(`http://ec2-15-164-87-251.ap-northeast-2.compute.amazonaws.com:8080/users/${id}`)
          .then((res) => res.json())
          .then((data) => {
              setFormer(data.data);
          })
      };
      const setFormerInfo = () => {
        setformValue({
          country: former.country,
          introduction: former.introduction,
          link: former.link,
          nickname: former.nickname,
          title: former.title,
          userTags: [
            {
              tagName: "python"
            },
            {
              tagName: "java"
            }
          ]
        });
        console.log("form should be set ", formValue);
      }
      getFormer();
      setFormerInfo();
  }, [])

    const handleChange = (event) => {
        setformValue({
      ...formValue,
      [event.target.name]: event.target.value
    });
    console.log("val is ", event.taget.value);
    console.log("formvalue is ", formValue);
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
        <Navbar />
        <div id="edit-wrapper">
            <div id="edit-title">Edit Your Profile</div>
            <div>
                <form id="edit-form">
                <label for="nickname" className="label-info">Display name</label>
                <input name="nickname" type="text" className="nickname-input ipt" onChange={handleChange} placeholder={former.nickname}/>
                <label for="country" className="label-info">Location</label>
                <input name="country" type="text" className="country-input ipt" onChange={handleChange} placeholder={former.country}/>
                <label for="title" className="label-info">Title</label>
                <input name="title" type="text" className="title-input ipt" onChange={handleChange} placeholder={former.title}/>
                <label for="introduction" className="label-info">About me</label>
                <input name="introduction" type="text" className="introduction-input ipt" onChange={handleChange} placeholder={former.introduction}/>
                <label for="link" className="label-info">Link</label>
                <input name="link" type="text" className="link-input ipt" onChange={handleChange} placeholder={former.link}/>
                <div id="edit-buttons-container">
                  <button className="edit-page-button" onClick={patchUser} type="submit">Save</button>
                  <button className="edit-page-button" onClick={goBack}>Cancel</button>
                  </div>
                </form>
            </div>
        </div>
      </div>
    );
}

export default EditUser