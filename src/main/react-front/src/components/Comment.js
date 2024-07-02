import 'bootstrap/dist/css/bootstrap.min.css';
import React, {useEffect, useState} from 'react';
import {getComment, postComment} from "../api/comment.service";
import {gameName} from "../api/main.service";
import {Button, Form, Table} from "react-bootstrap";
import { useForm } from "react-hook-form"

function Comment(inputData) {
    const getData = () => {
        getComment(gameName).then(response => {
            setComments(response.data);
        });
    }

    const {
        register,
        handleSubmit,
        formState: { errors},
    } = useForm({mode: 'onChange'})

    const onSubmit = (data) => {
        postComment(gameName, inputData.player.name, data.comment).then(response => {
            getData();
        });

    }

    const [comments, setComments] = useState([]);

    useEffect(() => {
        getData();
    }, []);

    return (
        <div>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>PLayer</th>
                        <th>Comment</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                {comments.map(comment => (
                    <tr key={comment.ident}>
                        <th>{comment.player}</th>
                        <th>{comment.comment}</th>
                        <th>{new Date(comment.commentedon).toLocaleString()}</th>
                    </tr>
                ))}
                </tbody>
            </Table>
            <Form onSubmit={handleSubmit(onSubmit)}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Comment</Form.Label>
                    <input className="form-control" type="text" placeholder="Enter comment"
                        {...register("comment", {
                            minLength: {value: 5, message: "Min is 5 characters"},
                            required: {value: true, message: "Comment is required"}
                        })}/>
                    <Form.Text style={{color:'red', float: 'right'}}>
                        {errors.comment?.message}
                    </Form.Text>
                </Form.Group>
                <Button type="submit">Push</Button>
            </Form>

        </div>
    );
}

export default Comment;
