import Link from "next/link";

export default function Home() {
  return (<>
    <h1>Fundametals</h1>

    <h3><Link href="/basic">Basic</Link></h3>
    <h3><Link href="/intermediate">Intermediate</Link></h3>
    <h3><Link href="/routes/victor">Intermediate: Routes - victor</Link></h3>
    <h3><Link href="/routes/name-info/item-info">Intermediate: Routes - name-info and item</Link></h3>
    <h3><Link href="/advance">Advance</Link></h3>
  </>);
}
