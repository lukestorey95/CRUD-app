from django.urls import path
from . import views

urlpatterns = [
    path("", views.home, name="home"),
    path("note/<str:id>/", views.note, name="note"),
    path("create-note/", views.createNote, name="create-note"),
    path("update-note/<str:id>/", views.updateNote, name="update-note"),
    path("delete-note/<str:id>/", views.deleteNote, name="delete-note"),
]
