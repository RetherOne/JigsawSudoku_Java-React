import React, {useEffect, useState} from 'react';
import {Button, Form, Table} from "react-bootstrap";
import {gameName} from "../api/main.service";
import {useForm} from "react-hook-form";
import {getAverageRating, setRating} from "../api/rating.service";


function Rating(inputData) {
    const [avarRating, setAvarRating] = useState(0);


    const getAverRating = () => {
        getAverageRating(gameName).then(response => {
            setAvarRating(response.data);
        });
    }



    const onSubmit = (data) => {
        setRating(gameName, inputData.player.name, parseInt(data.rating)).then(response => {
            getAverRating();
        });
    };



    const {
        register,
        handleSubmit
    } = useForm({mode: 'onChange'})

    useEffect(() => {
        getAverRating();
    });

    return (
        <div>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>PLayer</th>
                    <th>Average Rating</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>{inputData.player.name}</th>
                        <th>{avarRating}</th>
                    </tr>
                </tbody>
            </Table>

            <Form className="login100-form validate-form" onSubmit={handleSubmit(onSubmit)}>
                <Form.Group className="mb-3">
                    <Form.Label>Select rating</Form.Label>
                    <Form.Select {...register("rating")}>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </Form.Select>
                </Form.Group>
                <Button type="submit">Submit</Button>
            </Form>
        </div>
    )
};

export default Rating;
