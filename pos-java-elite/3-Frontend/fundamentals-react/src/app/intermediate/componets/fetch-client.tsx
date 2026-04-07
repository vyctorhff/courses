'use client';

import { FC, useEffect, useState } from "react";

/**
 * CORS
 * 
 * Proteção feita do servidor que o navegador respeita ao fazer uma requisição.
 * 
 * Fetch no client é usado quando:
 *  - dado em tempo real
 *  - depende da interação com o usuário
 *  - requisição 'pesada': relatório
 */

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
        }, 6000);
    });
    
    if (loading) return (<>Fetch Client: Loading fetch data....</>);

    return (<>
        <h4>Fetch Client</h4>
        <span>id: {data?.id} - title: {data?.title}</span>
    </>);
};
