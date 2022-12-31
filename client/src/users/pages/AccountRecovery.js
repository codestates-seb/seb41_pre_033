import { useState } from 'react';
import axios from 'axios';

export const AccountRecovery = () => {
    const [formValue, setformValue] = useState({
        email: ''
      });
    const handleChange = (event) => {
        setformValue({
          ...formValue,
          [event.target.name]: event.target.value
        });
    }
    const recovery = (e) => {
        e.preventDefault();
        axios.post({
          method: "POST",
          url: "http://ec2-43-201-146-208.ap-northeast-2.compute.amazonaws.com:8080/users/account-recovery",
          body: JSON.stringify(formValue),
        })
        .then((response) => response.json())
        .then((data) => {
          console.log("data is ", data);
          if (data.error) {
            alert("Error"); /*displays error message*/
          } else {
            alert("email sent");
          }
        })
        .catch((err) => {
          console.log(err);
        });
      };

    return (
        <div>
            <div id="account-recovery-title">Account Recovery</div>
            <div id="email-input-container">
                <div id="explanation">Enter your email address to receive a temporary password</div>
                <form>
                    <input name="email" type="email" onChange={handleChange}/>
                    <button onClick={recovery}>Submit</button>
                </form>
            </div>
        </div>
    )
}