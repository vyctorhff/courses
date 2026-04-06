'use client';

import { FC, useEffect, useState } from "react";

type Post = {
    id: number,
    title: string,
}

export const FetchClient:FC = () => {
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState<Post>();
    
    useEffect(() => {
        setTimeout(() => {
            setLoading(false);

            fetch('https://jsonplaceholder.typicode.com/todos/1')
                .then(response => response.json())
                .then(json => setData({id: json.id, title: json.title}));
        }, 5000);
    });
    
    if (loading) return (<>Loading fetch data....</>);

    return (<>
        <h4>Resultado fetch</h4>
        <span>id: {data?.id} - title: {data?.title}</span>
    </>);
};
