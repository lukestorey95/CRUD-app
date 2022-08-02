import type { NextPage, GetServerSideProps } from 'next'
import { useState } from 'react'
import { prisma } from "../lib/prisma"
import { useRouter } from 'next/router'


interface FormData {
  note: string
  id: string
}

interface Notes {
  notes: {
    id: string
    note: string
  }[]
}

const Home = ({ notes }: Notes) => {
  const [form, setForm] = useState<FormData>({ note: "", id: "" })
  const router = useRouter()

  const refreshData = () => {
    router.replace(router.asPath)
  }
  
  async function create(data: FormData) {
    try {
      fetch('http://localhost:3000/api/create', {
        body: JSON.stringify(data),
        headers: { 'Content-Type': 'application/json' },
        method: 'POST'
      }).then(() => {
        setForm({ note: "", id: "" })
        refreshData()
      })
    } catch (error) {
      console.log(error)
    }
  }

  async function deleteNote(id: string) {
    try {
      fetch(`http://localhost:3000/api/note/${id}`, {
        headers: { 'Content-Type': 'application/json' },
        method: "DELETE"
      }).then(() => {
        refreshData()
      })
    } catch (error) {
      console.log(error)
    }
  }

  // async function updateNote(data: FormData) {
  //   try {
  //     fetch(`http://localhost:3000/api/note/${id}`, {
  //       headers: { 'Content-Type': 'application/json' },
  //       method: "PUT"
  //     }).then(() => {
  //       refreshData()
  //     })
  //   } catch (error) {
  //     console.log(error)
  //   }
  // }

  function updateNote() {

  }

  const handleSubmit = async (data: FormData) => {
    try {
      create(data)
    } catch (error) {
      console.log(error)
    }
  }

  return (
    <div>
      <h1>Notes</h1>
      <form onSubmit={e => {
        e.preventDefault()
        handleSubmit(form)
      }}>
        <input type="text" placeholder="what do you want to note?" value={form.note} onChange={e => setForm({...form, note: e.target.value})}/>
        <button type="submit">Add</button>
      </form>

      <div>
        <ul>
          {notes.map(note => {
            return (
              <li key={note.id}>
                <h3>{note.note}</h3>
                <button onClick={() => deleteNote(note.id)}>Delete</button>
                <button onClick={() => updateNote()}>Update</button>
              </li>
            )
          })}
        </ul>
      </div>
    </div>
  )
}

export default Home


export const getServerSideProps: GetServerSideProps = async () => {
  const notes = await prisma.note.findMany({
    select: {
      note: true,
      id: true
    }
  })

  return {
    props: {
      notes
    }
  }
}