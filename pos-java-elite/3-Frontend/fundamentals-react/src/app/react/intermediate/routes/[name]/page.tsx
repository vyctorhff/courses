'use client';

import Link from 'next/link';
import { useParams } from 'next/navigation';

import { HelloName } from '../../basic/components/hello-comp'

// Ver arquivo: routes/[tag]/[item]/pages.tsx

type PageProps = {
    params: Promise<{name: string}>;
}

// const Page = async (props:{ params: Promise<{ name: string}> }) => {
//ou
const Page = async (props: PageProps) => {
    const {name} = await props.params;

    return (<>
        <HelloName name={name}/>
        <Link href="/routes">Go back</Link>
    </>);
}

export default Page;
