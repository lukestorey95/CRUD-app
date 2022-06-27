from django.shortcuts import render, redirect
from .models import Note
from .forms import NoteForm


def home(request):
    notes = Note.objects.all()
    context = {'notes': notes}
    return render(request, 'base/home.html', context)


def note(request, id):
    note = Note.objects.get(id=id)
    context = {'note': note}
    return render(request, 'base/note.html', context)


def createNote(request):
    form = NoteForm()
    if request.method == 'POST':
        form = NoteForm(request.POST)
        if form.is_valid():
            form.save()
            return redirect("home")

    context = {'form': form}
    return render(request, "base/note_form.html", context)


def updateNote(request, id):
    note = Note.objects.get(id=id)
    form = NoteForm(instance=note)
    if request.method == 'POST':
        form = NoteForm(request.POST, instance=note)
        if form.is_valid():
            form.save()
            return redirect("home")

    context = {'form': form}
    return render(request, 'base/note_form.html', context)


def deleteNote(request, id):
    note = Note.objects.get(id=id)

    if request.method == 'POST':
        note.delete()
        return redirect("home")
