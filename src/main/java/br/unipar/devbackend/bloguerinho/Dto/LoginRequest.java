package br.unipar.devbackend.bloguerinho.Dto;

public record LoginRequest(
        String username,
        String password) {
    @Override
    public String password() {
        return password;
    }

    @Override
    public String username() {
        return username;
    }

}
