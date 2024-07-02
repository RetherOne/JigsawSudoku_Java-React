import React from 'react';
import {Button, Form} from "react-bootstrap";
import {useForm} from "react-hook-form";
import {useNavigate} from "react-router-dom";

function Game({setUp}){
    const navigate = useNavigate();

    const {
        register,
        handleSubmit
    } = useForm()

    const onSubmit = (data) => {
        setUp(parseInt(data.level), parseInt(data.difficult));
        navigate('/play');
    }
    return(
        <div style={{height: '300px'}} className="center wrap-login100">

            <Form className="login100-form validate-form" onSubmit={handleSubmit(onSubmit)}>
                <Form.Group className="mb-3">
                    <Form.Label>Select level</Form.Label>
                    <Form.Select {...register("level")}>
                        <option value="1">Level 1</option>
                        <option value="2">Level 2</option>
                        <option value="3">Level 3</option>
                    </Form.Select>
                    <Form.Label>Select difficult</Form.Label>
                    <Form.Select {...register("difficult")}>
                        <option value="1">EASY</option>
                        <option value="2">MID</option>
                        <option value="3">HARD</option>
                    </Form.Select>
                </Form.Group>
                <Button type="submit">Submit</Button>
            </Form>
        </div>
    )
}


export default Game;
