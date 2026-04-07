'use client';

import { useParams } from "next/navigation";

/**
 * app/shop/[tag]/[item]/page.tsx
 * URL "/shop/shoes/nike" -> params is { tag: 'shoes', item: 'nike' }
 * 
 * const params = useParams();
 * params.tag
 * ou
 * const { tag, item } = useParams();
 */

export default function Page() {
    const { name, item } = useParams();

    return (<>
        <h3>Dados da navegacao</h3>
        <div>Tag: {name}</div>
        <div>Item: {item}</div>
    </>);
}