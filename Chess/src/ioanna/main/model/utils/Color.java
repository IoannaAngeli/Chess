package ioanna.main.model.utils;

public enum Color {

	BLACK, WHITE;

	Color nextColor() {
//		if(this.equals(BLACK))
//		{
//			return WHITE;
//		}
//		else
//		{
//			return BLACK;
//		}
		return this.equals(BLACK) ? WHITE : BLACK;
	}
}
