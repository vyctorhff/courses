import { FC, useEffect, useState } from "react";

/**
 * Fetch no servidor
 * 
 * Quando usar:
 *  - Interação com apis de outros sistemas
 */

type Post = {
    id: number,
    title: string,
}

const doRequest = async (): Promise<Post[]> => {
    const response = await fetch('https://jsonplaceholder.typicode.com/todos');
    let body: Post[] = await response.json();

    return body.slice(0, 5);// diminuindo resultado
};

export const FetchServer:FC = () => {
    const [loading, setLoading] = useState(true);
    const [postList, setPostList] = useState<Post[]>([]);

    useEffect(() => {
        const request = async () => {
            let body: Post[] = await doRequest();
            setPostList(body);
            setLoading(false);
        };
        
        setTimeout(() => {
            request();
        }, 6000);
    });

    if (loading) return (<><span>Loading fetch server...</span></>);

    return (<>
        <h4>Fetch Server</h4>
        {postList.map(post => <li key={post.id}>{post.title}</li>)}
    </>);
};