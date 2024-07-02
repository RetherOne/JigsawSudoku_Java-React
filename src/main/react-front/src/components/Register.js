import 'bootstrap/dist/css/bootstrap.min.css';
import {Button, Form} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import {useForm} from "react-hook-form";
import {addAccount, existAccount} from "../api/account.service";
import React, {useState} from "react";


function Register({handlerPlayer}){
    const [showError, setShowError] = useState(false);
    const navigate = useNavigate();
    const {
        register,
        handleSubmit,
        formState: { errors},
    } = useForm({mode: 'onChange'})

    const onSubmit = (data) => {
        existAccount(data.player, data.password).then(response => {
            if (!response.data) {
                addAccount(data.player, data.password).then(response => {
                    handlerPlayer(data.player);
                    navigate('/game')
                })
            }
            else{
                setShowError(true);
            }
        });
    }
    return (
        <div className="center wrap-login100">
            <span className="login100-form-title">Registration</span>
            <span style={{
                color: "#ce0000",
                visibility: showError ? "visible" : "hidden"
            }}>User already exists!<br/></span>

            <Form className="login100-form validate-form" onSubmit={handleSubmit(onSubmit)}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <input className="input100" type="text" placeholder="Enter login"
                           {...register("player", {
                               minLength: {value: 3, message: "Min is 3 characters"},
                               required: {value: true, message: "Login is required"}
                           })}/>
                    <Form.Text style={{color: '#ce0000', float: 'right'}}>
                        {errors.player?.message}
                    </Form.Text>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <input className="input100" type="text" placeholder="Enter password"
                           {...register("password", {
                               minLength: {value: 3, message: "Password is 3 characters"},
                               required: {value: true, message: "Password is required"}
                           })}/>
                    <Form.Text style={{color: '#ce0000', float: 'right'}}>
                        {errors.password?.message}
                    </Form.Text>
                </Form.Group>

                <Button type="submit">GO</Button>
            </Form>


        </div>
    );
}

export default Register;
