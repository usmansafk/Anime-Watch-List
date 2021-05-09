//-----------CRUD for an anime-----------
class AnimeWatchList {
  constructor(name, count, rating) {
    this.name = name;
    this.count = count;
    this.rating = rating;
  }
}

class CRUD {
  updateWatchList = false;
  updateTarget = null;

  //READ
  static showAnimes() {
    const animes = Anime.getAnime();
    animes.forEach((anime) => {
      CRUD.addAnimeToWatchList(anime);
    });
  }
  //CREATE
  static addAnimeToWatchList(anime) {
    const table = document.querySelector("#watchlist");
    const row = document.createElement("tr");

    row.innerHTML = `
        <td>${anime.name}</td>
        <td>${anime.count}</td>
        <td>${anime.rating}</td>
        <td><i class="fas fa-trash-alt text-danger" onClick="CRUD.deleteAnime(event, '${anime.name}')"></i></td>
        <td><i class="far fa-edit text-primary" onClick="CRUD.updateAnime(event, '${anime.name}')"></i></td>`;
    table.appendChild(row);
  }

  //READ>if already exists
  static checkForAnime(name) {
    const animes = Anime.getAnime();
    let alert = false;

    animes.forEach((show, id) => {
      if (show.name === name) {
        alert = true;
      }
    });
    return alert;
  }

  //UPDATE
  static updateAnimeInWatchList(anime) {
    if (CRUD.updateTarget && CRUD.updateWatchList) {
      let row = CRUD.updateTarget.parentElement.parentElement;
      row.innerHTML = `
          <td>${anime.name}</td>
          <td>${anime.count}</td>
          <td>${anime.rating}</td>
          <td><i class="fas fa-trash-alt text-danger" onClick="CRUD.deleteAnime(event, '${anime.name}')"></i></td>
          <td><i class="far fa-edit text-primary" onClick="CRUD.updateAnime(event, '${anime.name}')"></i></td>`;
    }
  }

  // () for UPDATING and DELETING
  static updateAnime(event, name) {
    CRUD.updateTarget = event.target;
    CRUD.updateWatchList = true;

    let animeList = Anime.getAnime();
    animeList.forEach((anime, id) => {
      if (anime.name === name) {
        document.querySelector("#name").value = anime.name;
        document.querySelector("#count").value = anime.count;
        document.querySelector("#rating").value = anime.rating;
      }
    });

    document.getElementById("create-button").innerHTML = "UPDATE ANIME";
  }

  static deleteAnime(event, name) {
    event.target.parentElement.parentElement.remove();
    Anime.deleteAnime(name);
    CRUD.confirmCRUD("Anime removed from watch list", "success");
  }

  static refreshInputBox() {
    document.querySelector("#name").value = "";
    document.querySelector("#count").value = "";
    document.querySelector("#rating").value = "";
    CRUD.updateWatchList = false;
    CRUD.updateTarget = null;
    document.querySelector("#name").disabled = false;
    document.getElementById("create-button").innerHTML = "ADD TO MY WATCHLIST";
  }

  static confirmCRUD(message, classname) {
    const div = document.createElement("div");
    div.className = `alert alert-${classname}`;
    div.innerHTML = message;
    const container = document.querySelector(".container");
    container.insertBefore(div, document.querySelector("#anime-form"));

    setTimeout(() => {
      document.querySelector(".alert").remove();
    }, 3000);
  }
}
//------------------------------------------------------------------

class Anime {
  static getAnime() {
    let animes;
    if (localStorage.getItem("animes") === null) {
      animes = [];
    } else {
      animes = JSON.parse(localStorage.getItem("animes"));
    }
    return animes;
  }

  static addAnime(anime) {
    const animes = Anime.getAnime();
    if (CRUD.updateWatchList) {
      Anime.updatedAnime(anime);
    } else {
      animes.push(anime);
      localStorage.setItem("animes", JSON.stringify(animes));
    }
  }

  static deleteAnime(name) {
    const animes = Anime.getAnime();
    animes.forEach((anime, id) => {
      if (anime.name === name) {
        animes.splice(id, 1);
      }
    });

    localStorage.setItem("animes", JSON.stringify(animes));
  }

  static updatedAnime(anime) {
    const animes = Anime.getAnime();
    animes.forEach((show, id) => {
      if (show.name === anime.name) {
        animes[id] = anime;
      }
    });

    localStorage.setItem("animes", JSON.stringify(animes));
  }
}
//------------------------------------------------------------------
document.addEventListener("DOMContentLoaded", CRUD.showAnimes());

document.querySelector("#anime-form").addEventListener("submit", (e) => {
  e.preventDefault();
  const name = document.querySelector("#name").value;
  const count = document.querySelector("#count").value;
  const rating = document.querySelector("#rating").value;

  if (name === "") {
    CRUD.confirmCRUD("Please enter an anime name ", "danger");
  } else if (count === "") {
    CRUD.confirmCRUD("Please enter which episode you are on", "danger");
  } else if (rating === "") {
    CRUD.confirmCRUD("Please enter your rating for the show", "danger");
  } else {
    const anime = new AnimeWatchList(name, count, rating);

    if (CRUD.updateWatchList) {
      CRUD.updateAnimeInWatchList(anime);
      message = "Anime updated";
      Anime.addAnime(anime);
      CRUD.confirmCRUD("Anime updated", "success");
      CRUD.refreshInputBox();
    } else {
      const nameExists = CRUD.checkForAnime(name);
      if (nameExists) {
        CRUD.confirmCRUD("This anime is already on your watch list", "danger");
      } else {
        CRUD.addAnimeToWatchList(anime);
        Anime.addAnime(anime);
        CRUD.confirmCRUD("Anime added", "success");
        CRUD.refreshInputBox();
      }
    }
  }
});
