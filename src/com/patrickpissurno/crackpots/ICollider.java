package com.patrickpissurno.crackpots;

import java.awt.*;

public interface ICollider {
    boolean isColliding(Rectangle rectangle);
    Rectangle getBoundingBox();
}
